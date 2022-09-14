package com.jd.ecommerce.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.model.Pedido;
import com.jd.ecommerce.service.NotaFiscalService;

public class GerarNotaFiscalListener {

    NotaFiscalService service = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido) {
	if (isPago(pedido) && pedido.getNotaFiscal() == null) {
	    service.gerar(pedido);
	}
    }

    public boolean isPago(Pedido pedido) {
	return StatusPedido.PAGO.equals(pedido.getStatus());
    }
}
