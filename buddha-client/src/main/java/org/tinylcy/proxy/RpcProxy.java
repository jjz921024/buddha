package org.tinylcy.proxy;

import org.apache.log4j.Logger;
import org.tinylcy.RPCFuture;
import org.tinylcy.RpcClient;
import org.tinylcy.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chenyangli.
 */
public class RpcProxy<T> implements InvocationHandler, IAsyncObjectProxy {

    private static final Logger LOGGER = Logger.getLogger(RpcProxy.class);

    private Class<?> clazz;
    private RpcClient client;

    public RpcProxy(RpcClient client) {
        this.client = client;
    }

    public RpcProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    public <T> T newProxy(Class<T> clazz) {
        this.clazz = clazz;
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }

    public static <T> IAsyncObjectProxy newProxyAsync(Class<T> clazz) {
        return new RpcProxy<>(clazz);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcResponse response = (RpcResponse) client.call(clazz, method, args);
        return response.getResult();
    }

    @Override
    public RPCFuture call(String funcName, Object... args) {
        return null;
    }
}
