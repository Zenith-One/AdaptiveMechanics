package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.entity.Entity;

public interface AMModel {
    public void renderAll(Entity entity, float f, float f1, float f2, float f3, float f4, float f5);
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5);
}
