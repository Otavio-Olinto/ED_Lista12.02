package otavioolinto;

public class Lista<T> implements ILista<T> {
	
	// Variável para verificaão do tipo de classe que a lista está executando
	private Class<T> type;
	
	// Ponteiro da Lista
	No<T> primeiro;
	
	public Lista(Class<T> tipo) {
		
		primeiro = null;
		
		this.type = tipo;
	}
	
	// Método responsável por retornar o tipo de classe que a Lista está executando
	public Class<T> getType(){
		
		return this.type;
	}
	
	// Método responsável por retornar um nó específico da lista
	private No<T> getNo(int posicao)throws Exception{
		
		if(isEmpty()) {
			
			throw new Exception("Lista Vazia!\n");
		}
		
		int tamanho = size();
		
		if(posicao<0 || posicao>tamanho-1) {
			
			throw new Exception("Posição inválida!\n");
		}
		
		No<T> noAux = primeiro;
		
		for(int cont=0; cont<posicao; cont++) {
			noAux = noAux.proximo;
		}
		
		return noAux;
	}
	
	// Método responsável por verificar se a Lista está vazia
	public boolean isEmpty() {
		
		return (primeiro==null) ? true:false;
	}
	
	// Método responsável por retornar o número de elementos presentes na Lista
	public int size() {
		
		int tamanho = 0;
		
		if(!isEmpty()) {
			
			No<T> noAux = primeiro;
			
			while(noAux!=null) {
				
				tamanho++;
				noAux= noAux.proximo;
			}
		}
		
		return tamanho;
	}
	
	// Método responsável por adicionar um valor na primeira posição da Lista
	public void addFirst(T valor) {
		
		No<T> elemento = new No<>();
		elemento.dado = valor;
		
		elemento.proximo = primeiro;
		primeiro = elemento;
	}
	
	// Método responsável por adicionar um valor no final da Lista
	public void addLast(T valor)throws Exception{
		
		if(isEmpty()) {
			
			addFirst(valor);
			
		}else {
			
			No<T> elemento = new No<>();
			elemento.dado = valor;
			
			int tamanho = size();
			
			No<T> ultimo = getNo(tamanho-1);
			ultimo.proximo = elemento;
		}
	}
	
	// Método responsável por adicionar um valor em uma posição específica da Lista
	public void add(T valor, int posicao)throws Exception {
		
		int tamanho = size();
		
		if(posicao<0 || posicao>tamanho) {
			throw new Exception("Posição inválida!\n");
		}
		
		// Se a posição escolhida for o primeiro índice da Lista, isto é
		// posicao==0, logo é pra adicionar como primeiro elemento
		if(posicao==0) {
			
			addFirst(valor);
		
		// Se a posição escolhida for igual a quantidade de elementos da Lista
		// Logo adiciona o novo elemento como último da Lista
		// último atual posicao==tamanho-1, logo novo último posicao==tamanho
		}else if(posicao==tamanho) {
			
			addLast(valor);
		
		// Senão devemos saber qual é o elemento anterior a posição que desejamos adicionar
		// O novo elemento recebe a referencia anterior.proximo
		// e o anterior.proximo é mudado para o novo elemento
		}else {
			
			No<T> elemento = new No<>();
			elemento.dado = valor;
			
			No<T> anterior = getNo(posicao-1);
			elemento.proximo = anterior.proximo;
			anterior.proximo = elemento;
		}
	}
	
	// Método responsável por remover o primeiro valor da Lista (operação destrutiva)
	public void removeFirst()throws Exception{
		
		if(isEmpty()) {
			
			throw new Exception("Lista vazia!\n");
		}
		
		primeiro = primeiro.proximo;
	}
	
	// Método responsável por remover o últimmo valor da Lista (operação destrutiva)
	public void removeLast()throws Exception {
		
		if(isEmpty()) {
			
			throw new Exception("Lista vazia!\n");
		}
		
		int tamanho = size();
		
		if(tamanho==1) {
			
			removeFirst();
			
		}else {
			
			// Para removermos o último valor, basta passarmos a referência
			// do penúltimo para null. O ultimo elemento é tamamnho-1
			No<T> penultimo = getNo(tamanho-2);
			penultimo.proximo = null;
		}
	}
	
	// Método responsável por remover valor de uma posição específica da Lista (operação destrutiva)
	public void remove(int posicao)throws Exception {
		
		if(isEmpty()) {
			
			throw new Exception("Lista Vazia!\n");
		}
		
		int tamanho = size();
		
		if(posicao<0 || posicao>tamanho-1) {
			
			throw new Exception("Posição inválida!\n");
		}
		
		if(posicao==0) {
			
			removeFirst();
			
		}else if(posicao==(tamanho-1)) {
			
			removeLast();
			
		}else {
			
			No<T> anterior = getNo(posicao-1);
			No<T> atual = getNo(posicao);
			
			anterior.proximo = atual.proximo;
		}
	}
	
	// Método responsável por retornar o valor de uma posição específica da Lista
	public T get(int posicao)throws Exception {

		No<T> noAux = getNo(posicao);
		
		return noAux.dado;
	}
}
