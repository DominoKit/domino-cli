package org.dominokit.cli.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;

import static java.util.Objects.isNull;

public class TemplateProvider {

    private static Configuration cfg;

    public static void render(String templatePath, Map<String, Object> context, Writer out) throws IOException, TemplateException {
        Arrays.stream(Versions.values()).forEach(version -> context.put(version.name(), version.get()));

        Template template = getEngine().getTemplate(templatePath);
        template.process(context, out);
    }

    public static String render(String templatePath, Map<String, Object> context) throws IOException, TemplateException {
        Arrays.stream(Versions.values()).forEach(version -> context.put(version.name(), version.get()));
        StringWriter out = new StringWriter();
        Template template = getEngine().getTemplate(templatePath);
        template.process(context, out);
        return out.toString();
    }

    private static Configuration getEngine() {
            if (isNull(cfg)) {
                cfg = new Configuration(Configuration.VERSION_2_3_29);
                cfg.setClassLoaderForTemplateLoading(TemplateProvider.class.getClassLoader(), "/projects-templates");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
                cfg.setLogTemplateExceptions(false);
                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);

            }
            return cfg;
    }
}
