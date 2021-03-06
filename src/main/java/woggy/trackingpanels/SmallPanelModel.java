package woggy.trackingpanels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SmallPanelModel extends ModelBase
{
  //fields
    ModelRenderer panel;
  
  public SmallPanelModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      panel = new ModelRenderer(this, 0, 0);
      panel.addBox(-8F, -8F, -8F, 16, 2, 16);
      panel.setRotationPoint(0F, -2F, 0F);
      panel.setTextureSize(64, 32);
      panel.mirror = true;
      setRotation(panel, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    panel.render(f5);
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
    this.panel.rotateAngleX = f;
  }

}
