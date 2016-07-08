package com.lb.model;

/**
 * @author LiBing
 * @usage	easyui属性网络数据源模型
 * name			属性名
 * value		属性值
 * group 		属性分组
 * editor		属性编辑器
 */
public class Property {
	
	private String name;
	
	private Object value;
	
	private String group;
	
	private String editor;
	
	public Property(){
		super();
	}
	
	public Property(String name,Object value,String group,String editor){
		super();
		this.name=name;
		this.value=value;
		this.group=group;
		this.editor=editor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	
}
