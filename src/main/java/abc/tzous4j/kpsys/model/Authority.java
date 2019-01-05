package abc.tzous4j.kpsys.model;


import java.io.Serializable;

public class Authority implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tbuser tbuser;
	private boolean logined;

	public Authority(Tbuser tbuser, boolean logined) {
		this.tbuser = tbuser;
		this.logined = logined;
	}

	public Tbuser getTbuser() {
		return tbuser;
	}

	public void setTbuser(Tbuser tbuser) {
		this.tbuser = tbuser;
	}


	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

}
