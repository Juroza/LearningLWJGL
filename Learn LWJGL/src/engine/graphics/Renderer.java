package engine.graphics;
import static org.lwjgl.opengl.GL30.*;
public class Renderer {
	private Shader shader;
	public Renderer(Shader shader) {
		this.shader=shader;
	}
	public void renderMesh(Mesh mesh) {
		glBindVertexArray(mesh.getVAO());
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,mesh.getIBO());
		shader.use();
		glDrawElements(GL_TRIANGLES,mesh.getIndices().length,GL_FLOAT,0);
		//glDrawArrays(GL_TRIANGLES,0,3);
		shader.terminate();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}

}
