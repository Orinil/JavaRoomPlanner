package net.uun.java.planner.repository.utils;

import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.beans.JavaBean;

@Component
public class JPAStreamerBeanFactory implements FactoryBean<JPAStreamer> {

     @Autowired EntityManagerFactory emFactory;

    @Override
    public JPAStreamer getObject() throws Exception {
        return JPAStreamer.of(emFactory);
    }

    @Override
    public Class<?> getObjectType() {
        return JPAStreamer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
