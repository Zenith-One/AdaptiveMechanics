package zenithmods.AdaptiveMechanics.api.tile;

import zenithmods.AdaptiveMechanics.codechicken.lib.raytracer.IndexedCuboid6;

import java.util.List;

public interface ICustomRaytrace {
    public List<IndexedCuboid6> getTraceableCuboids();
}
