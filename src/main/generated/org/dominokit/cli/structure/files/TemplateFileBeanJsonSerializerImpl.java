package org.dominokit.cli.structure.files;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import org.dominokit.jacksonapt.JsonSerializationContext;
import org.dominokit.jacksonapt.JsonSerializer;
import org.dominokit.jacksonapt.ser.EnumJsonSerializer;
import org.dominokit.jacksonapt.ser.StringJsonSerializer;
import org.dominokit.jacksonapt.ser.bean.AbstractBeanJsonSerializer;
import org.dominokit.jacksonapt.ser.bean.BeanPropertySerializer;

public final class TemplateFileBeanJsonSerializerImpl extends AbstractBeanJsonSerializer<TemplateFile> {
  public TemplateFileBeanJsonSerializerImpl() {
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
