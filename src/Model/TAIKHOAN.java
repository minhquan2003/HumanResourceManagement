package Model;

public class TAIKHOAN {
	private String maTaiKhoan;
    private String username;
    private String pass;
    private String phanQuyen;
    private String maNhomQuyen;
    private String avatar;

    // Constructors
    public TAIKHOAN() {
    }

    public TAIKHOAN(String maTaiKhoan, String username, String pass, String phanQuyen, String maNhomQuyen, String avatar) {
    	this.maTaiKhoan = maTaiKhoan;
        this.username = username;
        this.pass = pass;
        this.phanQuyen = phanQuyen;
        this.maNhomQuyen = maNhomQuyen;
        this.avatar = avatar;
    }
    
    public TAIKHOAN(String maTaiKhoan, String username, String maNhomQuyen) {
    	this.maTaiKhoan = maTaiKhoan;
        this.username = username;
        this.maNhomQuyen = maNhomQuyen;
    }
    
    public TAIKHOAN(String maTaiKhoan, String username) {
    	this.maTaiKhoan = maTaiKhoan;
    	this.username = username;
    }

    // Getters and Setters
    public String getMaTaiKhoan() {
    	return this.maTaiKhoan;
    }
    
    public void setMaTaiKhoan(String maTaiKhoan) {
    	this.maTaiKhoan = maTaiKhoan;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public String getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(String maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "TAIKHOAN{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", phanQuyen='" + phanQuyen + '\'' +
                ", maNhomQuyen='" + maNhomQuyen + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
