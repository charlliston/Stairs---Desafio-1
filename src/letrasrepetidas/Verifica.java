import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;


public class Verifica {

  	public static void main(String[] args) {

		HashMap<Character, Integer> map = new HashMap<>(); //mapa para guardar caractere e total de ocorrências
                HashMap<Character, Integer> repetidas = new HashMap<>(); //mapa para guardar caracteres repetidos número par de vezes
		String string = "";
                Scanner input = new Scanner(System.in);
            
                System.out.println("Insira uma string: ");
                string = input.nextLine();

                while (string.length() < 2 || !somenteTexto(string)) {
                    try { //lança exceção caso o input for menor que 2
                        if(string.length() < 2) {
                           throw new TamanhoException(string);
                        }
                    }
                    catch (TamanhoException tamanhoException) { //captura e trata a exceção do tipo tamanhoException
                        System.err.println( "A string deve conter no mínimo 2 caracteres. Tente novamente: " );
                        string = input.nextLine();
                    }
                    if(!somenteTexto(string)) {
                    try { //lança exceção caso o input contenha caracteres inválidos
                        if(!somenteTexto(string)) {
                            throw new InvalidoException(string);
                        }
                    }
                    catch (InvalidoException invalidoException ) { //captura e trata exceção do tipo invalidoException
                        System.err.println( "Contém caractere(s) inválido(s). Tente novamente: " );
                        string = input.nextLine();
                    }
                    }
                }              
                
		for(int i = 0; i < string.length(); i++){  
                    char c = string.charAt(i); //transforma o input em chars individuais
			if(map.containsKey(c)) { //se o caractere existe em map, soma-se 1 ao seu valor total
                            map.put(c, map.get(c).intValue() + 1);
			}else{ //se o caractere não existe em map, atribui-se 1 ao seu valor total
                            map.put(c, 1);
			}
		}
                
                //percorre todo o map
                for(Entry<Character, Integer> entry : map.entrySet()){
                    if(entry.getValue() % 2 == 0) { //avalia se o caractere é repetido um número par de vezes
                        repetidas.put(entry.getKey(), entry.getValue()); //grava o caractere repetido um número par de vezes
                                                                                                            //em um novo mapa
                    }           
                }
                    if(repetidas.size() > 1 || repetidas.isEmpty()) { //avalia o tamanho do mapa ou se ele está vazio
                        System.out.println("--");
                    } else {
                        System.out.println(repetidas.keySet()); //retorna o único caractere repetido um número par de vezes
                        }     
        }	

    //método que define uma regra para o input
    private static boolean somenteTexto(String string) {
        return string.matches("[^\\ddA-Z]+");
    }

    //método que trata a exceção lançada se houver caracteres inválidos no input
    public static class InvalidoException extends RuntimeException {
        
        public InvalidoException(String string) {
            super("A string contém caracteres não permitidos, como números ou letras maiúsculas.");
        }
    }
    
    //método que trata a exceção lançada se o input não tiver pelo menos 2 caracteres
    public static class TamanhoException extends RuntimeException {
        
        public TamanhoException(String string) {
            super("A string não possui tamanho mínimo de elementos.");
        }
    }
}
