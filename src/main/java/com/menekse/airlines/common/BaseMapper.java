package com.menekse.airlines.common;

import java.util.List;

public interface BaseMapper<T, S> {

    T map(S source);

    List<T> map(List<S> source);
}
