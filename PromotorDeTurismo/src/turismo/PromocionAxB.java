package turismo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PromocionAxB implements Promocion {

	private List<Atraccion> atracciones;
	private Atraccion extra;
		
	public PromocionAxB(List<Atraccion> atracciones, Atraccion extra){
		this.extra = extra;
		this.atracciones = atracciones;
	}
		
	
	@Override
	public void agregarAtraccion(Atraccion atraccionNueva) {
		this.atracciones.add(atraccionNueva);		
	}

	@Override
	public List<Atraccion> getAtracciones() {
		
		return this.atracciones;		
	}

	@Override
	public double costoTotal() {
		double costo = 0;
		for (Atraccion atraccion : atracciones){
			costo = costo + atraccion.getCostoDeAtraccion();
		}
		return costo;
	}


	@Override
	public void aplicarPromocion(Sugerencia recorrido) {
		if (recorrido.getListaDeAtracciones().contains(this.extra)){ 
				recorrido.setCostoFinal(this.extra.getCostoDeAtraccion());
		}else{
			recorrido.agregarAtraccionExtra(this.extra);
		}
		
	}


	@Override
	public boolean esAplicableLaPromocion(Sugerencia recorrido) {
		Set<Atraccion> conjuntoDeAtracciones = new HashSet<Atraccion>();
		conjuntoDeAtracciones.addAll(recorrido.getListaDeAtracciones());
		return (conjuntoDeAtracciones.containsAll(this.atracciones));
	}
}
