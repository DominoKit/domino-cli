package org.dominokit.cli.structure.files;

import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import org.dominokit.jacksonapt.JacksonContextProvider;
import org.dominokit.jacksonapt.JsonDeserializationContext;
import org.dominokit.jacksonapt.JsonDeserializer;
import org.dominokit.jacksonapt.JsonDeserializerParameters;
import org.dominokit.jacksonapt.deser.EnumJsonDeserializer;
import org.dominokit.jacksonapt.deser.StringJsonDeserializer;
import org.dominokit.jacksonapt.deser.bean.AbstractBeanJsonDeserializer;
import org.dominokit.jacksonapt.deser.bean.BeanPropertyDeserializer;
import org.dominokit.jacksonapt.deser.bean.HasDeserializerAndParameters;
import org.dominokit.jacksonapt.deser.bean.Instance;
import org.dominokit.jacksonapt.deser.bean.InstanceBuilder;
import org.dominokit.jacksonapt.deser.bean.MapLike;
import org.dominokit.jacksonapt.stream.JsonReader;

public final class TemplateFileBeanJsonDeserializerImpl extends AbstractBeanJsonDeserializer<TemplateFile> {
  public TemplateFileBeanJsonDeserializerImpl() {
  }

  @Override
  public Class getDeserializedType() {
    return TemplateFile.class;
  }

  @Override
  protected InstanceBuilder<TemplateFile> initInstanceBuilder() {
    final MapLike<HasDeserializerAndParameters> deserializers = null;
    return new InstanceBuilder<TemplateFile>() {
      @Override
      public Instance<TemplateFile> newInstance(JsonReader reader, JsonDeserializationContext ctx,
          JsonDeserializerParameters params, Map<String, String> bufferedProperties,
          Map<String, Object> bufferedPropertiesValues) {
        return new Instance<TemplateFile>(create(), bufferedProperties);
      }

      @Override
      public MapLike<HasDeserializerAndParameters> getParametersDeserializer() {
        return deserializers;
      }

      private TemplateFile create() {
        return new TemplateFile();
      }
    };
  }

  @Override
  protected MapLike<BeanPropertyDeserializer<TemplateFile, ?>> initDeserializers() {
    MapLike<BeanPropertyDeserializer<TemplateFile, ?>> map = JacksonContextProvider.get().mapLikeFactory().make();
    map.put("name", new BeanPropertyDeserializer<TemplateFile, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(TemplateFile bean, String value, JsonDeserializationContext ctx) {
        bean.setName(value);
      }
    });
    map.put("template", new BeanPropertyDeserializer<TemplateFile, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(TemplateFile bean, String value, JsonDeserializationContext ctx) {
        bean.setTemplate(value);
      }
    });
    map.put("type", new BeanPropertyDeserializer<TemplateFile, TemplateType>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return EnumJsonDeserializer.newInstance(TemplateType.class);
      }

      @Override
      public void setValue(TemplateFile bean, TemplateType value, JsonDeserializationContext ctx) {
        bean.setType(value);
      }
    });
    return map;
  }
}
