package com.jon.hyf_blog.util;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.user.User;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.UnknownServiceException;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(Article.class)
                    .addAnnotatedClass(Tag.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
}