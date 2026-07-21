package daviddev.eventos.controller;

import daviddev.eventos.model.Convidado;
import daviddev.eventos.model.Evento;
import daviddev.eventos.repository.ConvidadoRepository;
import daviddev.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ConvidadoRepository convidadoRepository;

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String form(Evento evento) {

        eventoRepository.save(evento);

        return "redirect:/cadastrarEvento";
    }
    @RequestMapping("/eventos")
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Evento> eventos = eventoRepository.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }

    @RequestMapping(value="/{codigo}" , method = RequestMethod.GET )
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        Evento evento = eventoRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado: " + codigo));

        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);
        System.out.println("evento: " + evento);
        return mv;
    }

    @RequestMapping(value="/{codigo}" , method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado, RedirectAttributes attributes){
        Evento evento = eventoRepository.findById(codigo).orElse(null);

        if (evento == null) {
            throw new IllegalArgumentException("Evento inválido para o código: " + codigo);
        }

        convidado.setEvento(evento);
        convidadoRepository.save(convidado);

        // Adiciona o código de forma segura para o redirecionamento
        attributes.addAttribute("codigo", codigo);

        return "redirect:/{codigo}";
    }

}
