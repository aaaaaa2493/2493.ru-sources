package ru.vt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ResourceExistsService {

    @Autowired
    ResourceLoader resourceLoader;

    public boolean resourceExists(String resourcePath) {
        Resource resource = resourceLoader.getResource("classpath:/static" + resourcePath);
        return resource.exists();
    }

}
