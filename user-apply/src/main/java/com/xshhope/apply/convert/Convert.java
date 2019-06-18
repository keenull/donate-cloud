package com.xshhope.apply.convert;

/**
 * @author xshhope
 */
public interface Convert<S, T> {
    public T apply(S source);
}
