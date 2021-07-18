package engine.io;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.*;
public class Window {
	private int width,height;
	public String title;
	public long window,time;
	private int frame=0;
	public Window(int width,int height,String title) {
		this.width=width;
		this.height=height;
		this.title=title;
	}
	public void startTime() {
		time= System.currentTimeMillis();
	}
	public void create() {
	    //glfwWindowHint( GLFW_RESIZABLE, GL_TRUE );
		if(!glfwInit()) {
			System.out.println("Could not initialise GLFW");
			System.exit(-1);
		}
	    glfwWindowHint( GLFW_CONTEXT_VERSION_MAJOR, 3 );
	    glfwWindowHint( GLFW_CONTEXT_VERSION_MINOR, 3 );
	    glfwWindowHint( GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE );
	    glfwWindowHint( GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE );
		window= glfwCreateWindow(width, height, title, 0,0);
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		if(window==0) {
			System.out.println("Window could not be created");
			System.exit(-1);
		}
		GLFWVidMode vidMode= glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glfwSwapInterval(1);
		glfwSetWindowPos(window,(vidMode.width()- width)/2, (vidMode.height()-height)/2);
		glfwShowWindow(window);
		glfwDefaultWindowHints();
	}
	public Boolean close() {
		return glfwWindowShouldClose(window);
	}
	public int framecounter() {
		frame=frame+1;
		if(System.currentTimeMillis()>=time+1000) {
			glfwSetWindowTitle(window,title+" FPS:"+frame);
			frame=0;
			time=System.currentTimeMillis();
		}
		return frame;
		
	}
	public void input() {
		if(glfwGetKey(window, GLFW_KEY_ESCAPE)==GLFW_PRESS) {
			glfwSetWindowShouldClose(window,true);
			
		}
	}
	public void WINUPDATE() {
		glClearColor(1.0f,0.0f,0.0f,0.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();
	}
	public void swapBuffers() {
		glfwSwapBuffers(window);
	}
}
