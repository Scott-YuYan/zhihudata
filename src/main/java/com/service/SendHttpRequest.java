package com.service;

import com.dao.mysql.impl.AnswerBeanImpl;
import com.domain.AnswerBean;
import com.service.constant.HttpConstant;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendHttpRequest {
    public static final Logger logger = Logger.getLogger(SendHttpRequest.class);
    private AnswerBeanImpl answerBeanImpl;

    @Autowired
    public SendHttpRequest(AnswerBeanImpl answerBeanImpl) {
        this.answerBeanImpl = answerBeanImpl;
    }


    public List<AnswerBean> getContent() throws IOException {
        Document document = getDocumentFromUrl("https://www.zhihu.com/question/404779462");
        Elements elements = document.getElementsByClass("List-item");
        List<AnswerBean> result = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            Elements elementsTitle = element.getElementsByClass("ContentItem-meta");
            Element title = elementsTitle.first();
            String userName = title.getElementsByClass("UserLink-link").text();
            int vote = Integer.parseInt(title.getElementsByClass("Button Button--plain").text().split(" ")[0].replace(",", ""));
            Elements elementsContent = element.getElementsByClass("RichContent RichContent--unescapable");
            Element content = elementsContent.first();
            String articleContent = content.getElementsByTag("p").text();
            AnswerBean bean = new AnswerBean(i, userName, vote, articleContent);
            result.add(bean);
        }
        answerBeanImpl.saveAnswerBean(result);
        return result;
    }

    private Document getDocumentFromUrl(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(
                RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()
        ).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        httpGet.setHeader("Cookie", HttpConstant.MY_COOKIE);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity httpEntity = response.getEntity();
            InputStream content = httpEntity.getContent();
            String html = IOUtils.toString(content, StandardCharsets.UTF_8);
            Document document = Jsoup.parse(html);
            EntityUtils.consume(httpEntity);
            return document;
        }
    }
}
