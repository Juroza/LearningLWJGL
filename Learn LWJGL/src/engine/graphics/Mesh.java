package engine.graphics;
import static org.lwjgl.opengl.GL30.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
	private Vertex[] vertices;
	private int[] indices;
	private int vao,pbo,ibo;
	public Mesh(Vertex[]vertices,int[] indicies) {
		this.indices=indicies;
		this.vertices=vertices;
	}
	public void create() {
		System.out.println("HERE");
		vao=glGenVertexArrays();
		System.out.println("HERE");
		glBindVertexArray(vao);
		FloatBuffer positionBuffer= MemoryUtil.memAllocFloat(vertices.length*3);
		float[] positionData = new float[vertices.length*3];
		pbo=glGenBuffers();
		for(int i=0;i<vertices.length;i++) {
			positionData[i*3]= vertices[i].getPosition().getX();
			positionData[i*3+1]= vertices[i].getPosition().getY();
			positionData[i*3+2]= vertices[i].getPosition().getZ();
		}
		positionBuffer.put(positionData).flip();
		glBindBuffer(GL_ARRAY_BUFFER,pbo);
		glBufferData(GL_ARRAY_BUFFER,positionBuffer,GL_STATIC_DRAW);
		glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);
		glBindBuffer(GL_ARRAY_BUFFER,0);
		IntBuffer indiciesBuffer=MemoryUtil.memAllocInt(indices.length);
		indiciesBuffer.put(indices).flip();
		ibo=glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER,indiciesBuffer,GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		
	}
	public Vertex[] getVertices() {
		return vertices;
	}
	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}
	public int[] getIndices() {
		return indices;
	}
	public void setIndices(int[] indices) {
		this.indices = indices;
	}
	public int getVAO() {
		return vao;
	}
	public void setVAO(int vao) {
		this.vao = vao;
	}
	public int getPBO() {
		return pbo;
	}
	public void setPBO(int pbo) {
		this.pbo = pbo;
	}
	public int getIBO() {
		return ibo;
	}
	public void setIBO(int ibo) {
		this.ibo = ibo;
	}

}
