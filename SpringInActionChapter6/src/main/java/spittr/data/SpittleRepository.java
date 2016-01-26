package spittr.data;

import java.util.List;

import spittr.Spittle;

public interface SpittleRepository {
	/**
	 * Busca spittles.
	 * 
	 * @param max
	 *            - Max id de spittle.
	 * @param count
	 *            - maxima cuenta.
	 * @return Lista de spittles.
	 */
	List<Spittle> findSpittles(long max, int count);

	/**
	 * Encuentra un spittle especifico.
	 * 
	 * @param id
	 *            - Id de spittle.
	 * @return Spittle con id solicitado.
	 */
	Spittle findOne(Long id);
}
