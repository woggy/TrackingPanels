package woggy.trackingpanels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AxleModel extends ModelBase
{
  //fields
    ModelRenderer axle;
  
  public AxleModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      axle = new ModelRenderer(this, 0, 0);
      axle.addBox(-8F, -8F, -2F, 16, 10, 4);
      axle.setRotationPoint(0F, 0F, 0F);
      axle.setTextureSize(64, 32);
      axle.mirror = false;
      setRotation(axle, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    axle.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
