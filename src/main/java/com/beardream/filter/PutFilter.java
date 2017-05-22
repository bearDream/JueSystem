package com.beardream.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * Created by soft01 on 2017/5/18.
 * 允许put请求带参数
 */
@Component
public class PutFilter extends HttpPutFormContentFilter {
}