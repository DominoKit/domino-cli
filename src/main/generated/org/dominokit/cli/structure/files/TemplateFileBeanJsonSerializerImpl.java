package org.dominokit.cli.structure.files;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import org.dominokit.jackson.JsonSerializationContext;
import org.dominokit.jackson.JsonSerializer;
import org.dominokit.jackson.ser.EnumJsonSerializer;
import org.dominokit.jackson.ser.StringJsonSerializer;
import org.dominokit.jackson.ser.bean.AbstractBeanJsonSerializer;
import org.dominokit.jackson.ser.bean.BeanPropertySerializer;

public final class TemplateFileBeanJsonSerializerImpl extends AbstractBeanJsonSerializer<TemplateFile> {
  private static final TemplateFileBeanJsonSerializerImpl INSTANCE = new TemplateFileBeanJsonSerializerImpl();

  public TemplateFileBeanJsonSerializerImpl() {
  }

  public static TemplateFileBeanJsonSerializerImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public Class getSerializedType() {
    return TemplateFile.class;
  }

  @Override
  protected BeanPropertySerializer[] initSerializers() {
    BeanPropertySerializer[] result = new BeanPropertySerializer[3];
    result[0] = new BeanPropertySerializer<TemplateFile, String>("name") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(TemplateFile bean, JsonSerializationContext ctx) {
        return bean.getName();
      }
    };
    result[1] = new BeanPropertySerializer<TemplateFile, String>("template") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(TemplateFile bean, JsonSerializationContext ctx) {
        return bean.getTemplate();
      }
    };
    result[2] = new BeanPropertySerializer<TemplateFile, TemplateType>("type") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return EnumJsonSerializer.getInstance();
      }

      @Override
      public TemplateType getValue(TemplateFile bean, JsonSerializationContext ctx) {
        return bean.getType();
      }
    };
    return result;
  }
}
