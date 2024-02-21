package com.vtxlab.bootcamp.stockpriceboard.Service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vtxlab.bootcamp.stockpriceboard.Infra.BcUtil;
import com.vtxlab.bootcamp.stockpriceboard.Infra.Scheme;
import com.vtxlab.bootcamp.stockpriceboard.Infra.Syscode;
import com.vtxlab.bootcamp.stockpriceboard.Model.Quote;
import com.vtxlab.bootcamp.stockpriceboard.Service.QuoteService;

@Service
public class QuoteJsonPlaceHolder implements QuoteService {

  @Value(value = "${api.finnhub.domain}")
  private String domain;

  @Value(value = "${api.finnhub.endpoints.quote}")
  private String quoteEndpoint;

  @Value(value = "${api.finnhub.key}")
  private String key;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public Quote getQuotes(String symbol) throws Exception {
    String quoteUrl = BcUtil.quoteUrl(Scheme.HTTPS, domain, quoteEndpoint, symbol.toUpperCase(), key);

    Quote quote = restTemplate.getForObject(quoteUrl, Quote.class);
    if (quote == null)
      throw new RestClientException(Syscode.RCException.getMessage());

    return quote;

  }

}
