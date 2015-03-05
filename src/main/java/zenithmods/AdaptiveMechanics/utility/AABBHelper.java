package zenithmods.AdaptiveMechanics.utility;

public class AABBHelper {

    public static AABBHelper instance = new AABBHelper();

    public static AABBTuple getDefault(){
        AABBTuple tuple = new AABBTuple();
        return tuple;
    }

    public static AABBTuple getTuple(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax){
        AABBTuple tuple = new AABBTuple(xMin, xMax, yMin, yMax, zMin, zMax);
        return tuple;
    }


    public static class AABBTuple{
        private float xMin;
        private float xMax;
        private float yMin;
        private float yMax;
        private float zMin;
        private float zMax;

        protected AABBTuple(){
            this.xMin = 0;
            this.xMax = 1;

            this.yMin = 0;
            this.yMax = 1;

            this.zMin = 0;
            this.zMax = 1;
        }

        public AABBTuple(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
            this();

            this.xMin = xMin;
            this.xMax = xMax;

            this.yMin = yMin;
            this.yMax = yMax;

            this.zMin = zMin;
            this.zMax = zMax;
        }

        public float getxMin() {
            return xMin;
        }

        public void setxMin(float xMin) {
            this.xMin = xMin;
        }

        public float getxMax() {
            return xMax;
        }

        public void setxMax(float xMax) {
            this.xMax = xMax;
        }

        public float getyMin() {
            return yMin;
        }

        public void setyMin(float yMin) {
            this.yMin = yMin;
        }

        public float getyMax() {
            return yMax;
        }

        public void setyMax(float yMax) {
            this.yMax = yMax;
        }

        public float getzMin() {
            return zMin;
        }

        public void setzMin(float zMin) {
            this.zMin = zMin;
        }

        public float getzMax() {
            return zMax;
        }

        public void setzMax(float zMax) {
            this.zMax = zMax;
        }
    }
}
