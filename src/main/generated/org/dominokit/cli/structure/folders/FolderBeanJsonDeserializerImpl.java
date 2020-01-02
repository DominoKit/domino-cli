package org.dominokit.cli.structure.folders;

import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
import org.dominokit.cli.structure.files.TemplateFile;
import org.dominokit.cli.structure.files.TemplateFileBeanJsonDeserializerImpl;
import org.dominokit.jacksonapt.JacksonContextProvider;
import org.dominokit.jacksonapt.JsonDeserializationContext;
import org.dominokit.jacksonapt.JsonDeserializer;
import org.dominokit.jacksonapt.JsonDeserializerParameters;
import org.dominokit.jacksonapt.deser.StringJsonDeserializer;
import org.dominokit.jacksonapt.deser.bean.AbstractBeanJsonDeserializer;
import org.dominokit.jacksonapt.deser.bean.BeanPropertyDeserializer;
import org.dominokit.jacksonapt.deser.bean.HasDeserializerAndParameters;
import org.dominokit.jacksonapt.deser.bean.Instance;
import org.dominokit.jacksonapt.deser.bean.InstanceBuilder;
import org.dominokit.jacksonapt.deser.bean.MapLike;
import org.dominokit.jacksonapt.deser.collection.ListJsonDeserializer;
import org.dominokit.jacksonapt.stream.JsonReader;

public final class FolderBeanJsonDeserializerImpl extends AbstractBeanJsonDeserializer<Folder> {
  public FolderBeanJsonDeserializerImpl() {
  }

  @Override
  public Class getDeserializedType() {
    return Folder.class;
  }

  @Override
  protected InstanceBuilder<Folder> initInstanceBuilder() {
    final MapLike<HasDeserializerAndParameters> deserializers = null;
    return new InstanceBuilder<Folder>() {
      @Override
      public Instance<Folder> newInstance(JsonReader reader, JsonDeserializationContext ctx,
          JsonDeserializerParameters params, Map<String, String> bufferedProperties,
          Map<String, Object> bufferedPropertiesValues) {
        return new Instance<Folder>(create(), bufferedProperties);
      }

      @Override
      public MapLike<HasDeserializerAndParameters> getParametersDeserializer() {
        return deserializers;
      }

      private Folder create() {
        return new Folder();
      }
    };
  }

  @Override
  protected MapLike<BeanPropertyDeserializer<Folder, ?>> initDeserializers() {
    MapLike<BeanPropertyDeserializer<Folder, ?>> map = JacksonContextProvider.get().mapLikeFactory().make();
    map.put("name", new BeanPropertyDeserializer<Folder, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Folder bean, String value, JsonDeserializationContext ctx) {
        bean.setName(value);
      }
    });
    map.put("condition", new BeanPropertyDeserializer<Folder, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Folder bean, String value, JsonDeserializationContext ctx) {
        bean.setCondition(value);
      }
    });
    map.put("folders", new BeanPropertyDeserializer<Folder, List<Folder>>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return ListJsonDeserializer.newInstance(new FolderBeanJsonDeserializerImpl());
      }

      @Override
      public void setValue(Folder bean, List<Folder> value, JsonDeserializationContext ctx) {
        bean.setFolders(value);
      }
    });
    map.put("files", new BeanPropertyDeserializer<Folder, List<TemplateFile>>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return ListJsonDeserializer.newInstance(new TemplateFileBeanJsonDeserializerImpl());
      }

      @Override
      public void setValue(Folder bean, List<TemplateFile> value, JsonDeserializationContext ctx) {
        bean.setFiles(value);
      }
    });
    return map;
  }
}
