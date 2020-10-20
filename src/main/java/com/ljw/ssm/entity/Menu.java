package com.ljw.ssm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ����
    private Integer id;
    
    // ���ڵ�ID
    private Integer pid;
    
    // �ڵ�����
    private String name;

    // �ڵ㸽����URL��ַ���ǵ���˵�����ת�ĵ�ַ
    private String url;

    // �ڵ�ͼ����ʽ
    private String icon;
    
    // �洢�ӽڵ�ļ��ϣ���ʼ����Ϊ�˱����ָ��
    private List<Menu> children = new ArrayList<>();
    
    // ���ƽڵ��Ƿ�Ĭ�ϴ�
    private Boolean open = true;
    
    
    

    @Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", name=" + name + ", url=" + url + ", icon=" + icon + ", children="
				+ children + ", open=" + open + "]";
	}
	public Menu(Integer id, Integer pid, String name, String url, String icon, List<Menu> children, Boolean open) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.children = children;
		this.open = open;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Integer getId() {
        return id; 
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}