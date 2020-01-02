package org.dominokit.cli.structure.folders;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import org.dominokit.cli.structure.files.TemplateFile;
import org.dominokit.cli.structure.files.TemplateFileBeanJsonSerializerImpl;
import org.dominokit.jacksonapt.JsonSerializationContext;
import org.dominokit.jacksonapt.JsonSerializer;
import org.dominokit.jacksonapt.ser.CollectionJsonSerializer;
import org.dominokit.jacksonapt.ser.StringJsonSerializer;
import org.dominokit.jacksonapt.ser.bean.AbstractBeanJsonSerializer;
import org.dominokit.jacksonapt.ser.bean.BeanPropertySerializer;

public final class FolderBeanJsonSerializerImpl extends AbstractBeanJsonSerializer<Folder> {
  public FolderBeanJsonSerializerImpl() {
  }

  @Override
  public Class getSerializedType() {
    return Folder.class;
  }

  @Override
  protected BeanPropertySerializer[] initSerializers() {
    BeanPropertySerializer[] result = new BeanPropertySerializer[4];
    result[0] = new BeanPropertySerializer<Folder, String>("name") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Folder bean, JsonSerializationContext ctx) {
        return bean.getName();
      }
    };
    result[1] = new BeanPropertySerializer<Folder, String>("condition") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return StringJsonSerializer.getInstance();
      }

      @Override
      public String getValue(Folder bean, JsonSerializationContext ctx) {
        return bean.getCondition();
      }
    };
    result[2] = new BeanPropertySerializer<Folder, List<Folder>>("folders") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return CollectionJsonSerializer.newInstance(new FolderBeanJsonSerializerImpl());
      }

      @Override
      public List<Folder> getValue(Folder bean, JsonSerializationContext ctx) {
        return bean.getFolders();
      }
    };
    result[3] = new BeanPropertySerializer<Folder, List<TemplateFile>>("files") {
      @Override
      protected JsonSerializer<?> newSerializer() {
        return CollectionJsonSerializer.newInstance(new TemplateFileBeanJsonSerializerImpl());
      }

      @Override
      public List<TemplateFile> getValue(Folder bean, JsonSerializationContext ctx) {
        return bean.getFiles();
      }
    };
    return result;
  }
}
