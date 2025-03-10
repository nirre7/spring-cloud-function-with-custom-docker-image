package com.example.demo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.joda.time.DateTime;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
@RegisterReflectionForBinding({DateTime.class, APIGatewayProxyRequestEvent.class, HashSet.class, HashMap.class, Map.class,
        APIGatewayProxyRequestEvent.ProxyRequestContext.class, APIGatewayProxyRequestEvent.RequestIdentity.class
})
@ImportRuntimeHints(GraalConfiguration.ApplicationRuntimeHintsRegistrar.class)
public class GraalConfiguration {

    public static class ApplicationRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        }
    }
}
