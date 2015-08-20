package register;

public interface Registerable {

	void writeRegister(Register register) throws Exception;

	Register registerLoad() throws Exception;

}