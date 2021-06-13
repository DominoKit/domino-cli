package org.dominokit.cli.model;

import java.lang.Boolean;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import org.dominokit.jackson.JacksonContextProvider;
import org.dominokit.jackson.JsonDeserializationContext;
import org.dominokit.jackson.JsonDeserializer;
import org.dominokit.jackson.JsonDeserializerParameters;
import org.dominokit.jackson.deser.BooleanJsonDeserializer;
import org.dominokit.jackson.deser.StringJsonDeserializer;
import org.dominokit.jackson.deser.bean.AbstractBeanJsonDeserializer;
import org.dominokit.jackson.deser.bean.BeanPropertyDeserializer;
import org.dominokit.jackson.deser.bean.HasDeserializerAndParameters;
import org.dominokit.jackson.deser.bean.Instance;
import org.dominokit.jackson.deser.bean.InstanceBuilder;
import org.dominokit.jackson.deser.bean.MapLike;
import org.dominokit.jackson.stream.JsonReader;

public final class ProjectBeanJsonDeserializerImpl extends AbstractBeanJsonDeserializer<Project> {
  private static final ProjectBeanJsonDeserializerImpl INSTANCE = new ProjectBeanJsonDeserializerImpl();

  public ProjectBeanJsonDeserializerImpl() {
  }

  public static ProjectBeanJsonDeserializerImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public Class getDeserializedType() {
    return Project.class;
  }

  @Override
  protected InstanceBuilder<Project> initInstanceBuilder() {
    final MapLike<HasDeserializerAndParameters> deserializers = null;
    return new InstanceBuilder<Project>() {
      @Override
      public Instance<Project> newInstance(JsonReader reader, JsonDeserializationContext ctx,
          JsonDeserializerParameters params, Map<String, String> bufferedProperties,
          Map<String, Object> bufferedPropertiesValues) {
        return new Instance<Project>(create(), bufferedProperties);
      }

      @Override
      public MapLike<HasDeserializerAndParameters> getParametersDeserializer() {
        return deserializers;
      }

      private Project create() {
        return new Project();
      }
    };
  }

  @Override
  protected MapLike<BeanPropertyDeserializer<Project, ?>> initDeserializers() {
    MapLike<BeanPropertyDeserializer<Project, ?>> map = JacksonContextProvider.get().mapLikeFactory().make();
    map.put("name", new BeanPropertyDeserializer<Project, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, String value, JsonDeserializationContext ctx) {
        bean.setName(value);
      }
    });
    map.put("groupId", new BeanPropertyDeserializer<Project, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, String value, JsonDeserializationContext ctx) {
        bean.setGroupId(value);
      }
    });
    map.put("artifactId", new BeanPropertyDeserializer<Project, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, String value, JsonDeserializationContext ctx) {
        bean.setArtifactId(value);
      }
    });
    map.put("version", new BeanPropertyDeserializer<Project, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, String value, JsonDeserializationContext ctx) {
        bean.setVersion(value);
      }
    });
    map.put("rootPackage", new BeanPropertyDeserializer<Project, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, String value, JsonDeserializationContext ctx) {
        bean.setRootPackage(value);
      }
    });
    map.put("moduleShortName", new BeanPropertyDeserializer<Project, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, String value, JsonDeserializationContext ctx) {
        bean.setModuleShortName(value);
      }
    });
    map.put("hasParent", new BeanPropertyDeserializer<Project, Boolean>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return BooleanJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, Boolean value, JsonDeserializationContext ctx) {
        bean.setHasParent(value);
      }
    });
    map.put("parentArtifactId", new BeanPropertyDeserializer<Project, String>() {
      @Override
      protected JsonDeserializer<?> newDeserializer() {
        return StringJsonDeserializer.getInstance();
      }

      @Override
      public void setValue(Project bean, String value, JsonDeserializationContext ctx) {
        bean.setParentArtifactId(value);
      }
    });
    return map;
  }
}
