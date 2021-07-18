package core;
import engine.io.*;
import engine.graphics.*;
import engine.maths.*;
public class Main{
	public static Shader shader;
	public static Window window;
	public static Renderer renderer;
	public static Mesh mesh= new Mesh(new Vertex[] {
			new Vertex(new Vector3f(-0.5f,  0.5f, 0.0f)),
			new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f)),
			new Vertex(new Vector3f( 0.5f, -0.5f, 0.0f)),
			new Vertex(new Vector3f( 0.5f,  0.5f, 0.0f))			
			
	},new int[] {
			0, 1, 2,
			0, 3, 2
	});
	
	public static void init() {
		System.out.println("Initialising");
		shader= new Shader("/shaders/mainVertexShader.glsl","/shaders/mainFragmentShader.glsl");
		window=new Window(700,700,"Testing");
		renderer= new Renderer(shader);
		window.create();
		mesh.create();
		shader.create();
	}
	public void run() {
		init();
		window.startTime();
		while(!window.close()) {
			window.framecounter();
			window.input();
			window.WINUPDATE();
			
			update();
			render();
		}
	}
	private void update() {
		//System.out.println("Updating;");
		
	}
	private void render() {
		renderer.renderMesh(mesh);
		window.swapBuffers();
		//System.out.println("Rendering");
	}

	public static void main(String[] args) {
		new Main().run();
		
		// TODO Auto-generated method stub

	}

}
