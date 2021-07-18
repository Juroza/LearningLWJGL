package engine.graphics;
import static org.lwjgl.opengl.GL20.*;

import engine.utils.FileUtils;

public class Shader {
	private String vertexFile,fragmentFile;
	private int vertexID,fragmentID,programID;
	public Shader(String vertexFile,String fragmentFile) {
		this.vertexFile=FileUtils.loadAsString(vertexFile);
		System.out.println(vertexFile);
		this.fragmentFile=FileUtils.loadAsString(fragmentFile);
		System.out.println(fragmentFile);
		
	}
	public void create() {
		System.out.println("HERE!");
		programID=glCreateProgram();
		vertexID= glCreateShader(GL_VERTEX_SHADER);
		fragmentID= glCreateShader(GL_FRAGMENT_SHADER);
		System.out.println("HERE!");

		getVertexShader();
		getFragmentShader();
		glAttachShader(programID,vertexID);
		glAttachShader(programID,fragmentID);
		glLinkProgram(programID);
		if(glGetProgrami(programID,GL_LINK_STATUS)==GL_FALSE ) {
			System.err.println("Couldn't link shaders:"+glGetProgramInfoLog(programID));
			return;
		}
		glValidateProgram(programID);
		if(glGetProgrami(programID,GL_VALIDATE_STATUS)==GL_FALSE) {
			System.err.println("Program not valid: "+glGetProgramInfoLog(programID));
			return;
		}

		deleteShaders();
	}
	public void use() {
		glUseProgram(programID);
	}
	public void terminate() {
		glUseProgram(programID);
	}
	public void destroy() {
		glDeleteProgram(programID);
	}
	private void deleteShaders() {
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
		
	}
	private void getVertexShader() {
		System.out.println(vertexFile+vertexID);
		

		glShaderSource(vertexID,vertexFile);;
		System.out.println("HEFFDFA!");

		glCompileShader(vertexID);
		if(glGetShaderi(vertexID,GL_COMPILE_STATUS)== GL_FALSE) {
			System.out.println("IJIJI");

			System.err.println("Couldn't compile VertexShader: "+glGetShaderInfoLog(vertexID));
			return;
		}
		System.out.println("HERE!");

		
	}
	private void getFragmentShader() {
		glShaderSource(fragmentID,fragmentFile);
		glCompileShader(fragmentID);
		if(glGetShaderi(fragmentID,GL_COMPILE_STATUS)==GL_FALSE) {
			System.err.println("Couldn't compile Fragmenthader: "+glGetShaderInfoLog(fragmentID));
			return;
		}
		System.out.println("HERE!");

	}
}
