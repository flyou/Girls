package com.flyou.girls.ui.typeImageList.model;

import com.flyou.girls.ui.typeImageList.domain.TypeImageDomain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.typeImageList.model
 * 文件名：TypeImageListModelImpl
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/20 14:43
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class TypeImageListModelImpl implements TypeImageListModel {
    @Override
    public void getTypeImageList(final String url, final TypeImageListModelImpl.OnGetTypeImageListener litener) {


        Observable<List<TypeImageDomain>> observable = Observable.create(new Observable.OnSubscribe<List<TypeImageDomain>>() {
            @Override
            public void call(Subscriber<? super List<TypeImageDomain>> subscriber) {
                List<TypeImageDomain> typeImageDomains = new ArrayList();
                try {
                    Document document = Jsoup.connect(url).get();
                    Element element = document.getElementsByClass("rgg-imagegrid").first();
                    Elements elementsA = element.getElementsByTag("a");

                    for (Element a : elementsA) {
                        String linkUrl = a.attr("abs:href");

                        Elements img = a.getElementsByTag("img");
                        String src = img.attr("src");
                        String width = img.attr("width");
                        String height = img.attr("height");
                        typeImageDomains.add(new TypeImageDomain(Integer.valueOf(width), Integer.valueOf(height), src,linkUrl));
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
                System.out.print(typeImageDomains.get(0).getHeight());
                subscriber.onNext(typeImageDomains);
            }
        });


        Subscriber<List<TypeImageDomain>> subscriber = new Subscriber<List<TypeImageDomain>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                litener.OnError((Exception) e);
            }

            @Override
            public void onNext(List<TypeImageDomain> typeImageDomains) {
                litener.onSuccess(typeImageDomains);

            }
        };


        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public interface OnGetTypeImageListener {
        void onSuccess(List<TypeImageDomain> imageDomainList);

        void OnError(Exception e);
    }
}
