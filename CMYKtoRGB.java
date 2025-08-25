public class CMYKtoRGB {

    // Convert CMYK values (0.0 to 1.0) to RGB (0 to 255)
    public static int[] convert(double c, double m, double y, double k) {
        int r = (int) Math.round(255 * (1 - c) * (1 - k));
        int g = (int) Math.round(255 * (1 - m) * (1 - k));
        int b = (int) Math.round(255 * (1 - y) * (1 - k));

        return new int[]{r, g, b};
    }

    public static void main(String[] args) {
        // Example: 100% Cyan (C=1, M=0, Y=0, K=0) -> RGB(0,255,255)
        double c = 1.0, m = 0.0, y = 0.0, k = 0.0;

        int[] rgb = convert(c, m, y, k);

        System.out.println("CMYK(" + c + "," + m + "," + y + "," + k + ")");
        System.out.println("RGB(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
    }
}
