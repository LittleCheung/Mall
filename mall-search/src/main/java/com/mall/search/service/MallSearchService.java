package com.mall.search.service;

import com.mall.search.vo.SearchParam;
import com.mall.search.vo.SearchResult;

/**
 * @author yaoxinjia
 */
public interface MallSearchService {
    SearchResult search(SearchParam param);
}
