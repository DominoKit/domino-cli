package org.dominokit.cli.model;

import java.lang.Boolean;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import org.dominokit.jackson.JsonSerializationContext;
import org.dominokit.jackson.JsonSerializer;
import org.dominokit.jackson.ser.BooleanJsonSerializer;
import org.dominokit.jackson.ser.StringJsonSerializer;
import org.dominokit.jackson.ser.bean.AbstractBeanJsonSerializer;
import org.dominokit.jackson.ser.bean.BeanPropertySerializer;

public final class ProjectBeanJsonSerializerImpl extends AbstractBeanJsonSerializer<Project> {
  private static final ProjectBeanJsonSerializerImpl INSTANCE = new ProjectBeanJsonSerializerImpl();

  public ProjectBeanJsonSerializerImpl() {
  }

  public static ProjectBeanJsonSerializerImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public Class getSerializedType() {
    return Project.class;
  }

  @Override
  protected BeanPropertySerializer[] initSerializers() {
    BeanPropertySerializer[] result = new BeanPropertySerializer[8];
    result[0] = new BeanPropertySerializer<Project, String>("name") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Project bean, JsonSerializationContext ctx) {
        return bean.getName();
      }
    };
    result[1] = new BeanPropertySerializer<Project, String>("groupId") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Project bean, JsonSerializationContext ctx) {
        return bean.getGroupId();
      }
    };
    result[2] = new BeanPropertySerializer<Project, String>("artifactId") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Project bean, JsonSerializationContext ctx) {
        return bean.getArtifactId();
      }
    };
    result[3] = new BeanPropertySerializer<Project, String>("version") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Project bean, JsonSerializationContext ctx) {
        return bean.getVersion();
      }
    };
    result[4] = new BeanPropertySerializer<Project, String>("rootPackage") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Project bean, JsonSerializationContext ctx) {
        return bean.getRootPackage();
      }
    };
    result[5] = new BeanPropertySerializer<Project, String>("moduleShortName") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Project bean, JsonSerializationContext ctx) {
        return bean.getModuleShortName();
      }
    };
    result[6] = new BeanPropertySerializer<Project, Boolean>("hasParent") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return BooleanJsonSerializer.getInstance();
      }

      @Override
      public Boolean getValue(Project bean, JsonSerializationContext ctx) {
        return bean.isHasParent();
      }
    };
    result[7] = new BeanPropertySerializer<Project, String>("parentArtifactId") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Project bean, JsonSerializationContext ctx) {
        return bean.getParentArtifactId();
      }
    };
    return result;
  }
}
