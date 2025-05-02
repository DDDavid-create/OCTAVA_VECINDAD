function calcular(event) {
            event.preventDefault();

            // Obtener valores del formulario
            const ninos = parseInt(document.getElementById('cantidad-ninos').value) || 0;
            const nombrePropietario = document.getElementById('nombre-propietario').value.trim();
            const nombreInquilino = document.getElementById('nombre-inquilino').value.trim();
            const numeroApto = parseInt(document.getElementById('numero-apartamento').value);
            const adultos = parseInt(document.getElementById('cantidad-adultos').value) || 0;

            // Validar ocupación: apartamento está ocupado si el inquilino tiene nombre
            const ocupado = nombreInquilino !== '';

            // Piscina: $2000 por adulto
            const costoPiscina = adultos * 2000;

            // Juegos: $5000 si hay niños
            const costoJuegos = ninos > 0 ? 5000 : 0;

            // Zonas sociales: se cobra solo si está ocupado, costo fijo $ ($10,000)
            const costoZonas = ocupado ? 10000 : 0;

            // Aseo: $15,000 solo para aptos 1 y 2 piso
            const costoAseo = (numeroApto >= 100 && numeroApto < 300) ? 15000 : 0;

            // Subtotal
            const subtotal = costoPiscina + costoJuegos + costoZonas + costoAseo;

            // Descuento: 20% si el dueño vive en el mismo
            const descuento = (nombreInquilino === nombrePropietario) ? subtotal * 0.20 : 0;

            // Total a pagar: costo fijo $50,000 + subtotal - descuento
            const totalPagar = 50000 + subtotal - descuento;

            // Mostrar resultados
            document.getElementById('detalle-piscina').textContent = `Piscina: $${costoPiscina.toLocaleString()} (${adultos} adulto${adultos !== 1 ? 's' : ''})`;
            document.getElementById('detalle-juegos').textContent = `Juegos: $${costoJuegos.toLocaleString()} (${ninos} niño${ninos !== 1 ? 's' : ''})`;
            document.getElementById('detalle-zonas').textContent = `Zonas sociales: $${costoZonas.toLocaleString()} (Apartamento ${ocupado ? 'ocupado' : 'desocupado'})`;
            document.getElementById('detalle-aseo').textContent = `Aseo: $${costoAseo.toLocaleString()} ( Apartamento : ${numeroApto >= 100 && numeroApto < 300 ? numeroApto : 'No aplica'})`;
            document.getElementById('subtotal').textContent = `Subtotal : $${subtotal.toLocaleString()}`;
            document.getElementById('detalle-descuento').textContent = `Descuento: -$${descuento.toLocaleString()} (${descuento > 0 ? '20% por dueño viviendo en el apto' : 'No aplica'})`;
            document.getElementById('total-pagar').textContent = `Total a pagar: $${totalPagar.toLocaleString()}`;

            document.getElementById('resultado').classList.remove('hidden');
            window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
        }