package com.xshhope.pay.convert;

/**
 * @author xshhope
 */
public interface Convert<S, T> {
    public T apply(S source);
}
