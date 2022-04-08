package com.mall.search.service;

import com.mall.search.vo.SearchParam;
import com.mall.search.vo.SearchResult;

/**
 *
 * @author littlecheung
 */
public interface MallSearchService {

    SearchResult search(SearchParam param);
}
