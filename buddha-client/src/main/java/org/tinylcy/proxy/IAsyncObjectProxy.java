package org.tinylcy.proxy;


import org.tinylcy.RPCFuture;

/**
 * Created by luxiaoxun on 2016/3/16.
 */
public interface IAsyncObjectProxy {
     RPCFuture call(String funcName, Object... args);
}