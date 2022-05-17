package com.soc.crud.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.soc.crud.dto.RequisicaoFormExame;
import com.soc.crud.models.StatusExame;
import com.soc.crud.models.Exame;
import com.soc.crud.repositories.ExameRepository;

@Controller
@RequestMapping(value = "/exames")
public class ExamesController {
    @Autowired
    private ExameRepository exameRepository;

    @GetMapping("/exames")
    public ModelAndView exames() {
       List<Exame> exames = this.exameRepository.findAll();
        ModelAndView mv = new ModelAndView("exames/exames");
        mv.addObject("exames", exames);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormExame requisicao) {
        ModelAndView mv = new ModelAndView("exames/new");
        mv.addObject("listaExame", StatusExame.values());

        return mv;
    }


    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormExame requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);
        if (bindingResult.hasErrors()) {
            System.out.println("************* TEM ERROS ***************");

            ModelAndView mv = new ModelAndView("exames/new");
            mv.addObject("listaExame", StatusExame.values());
            return mv;
        }
        else {
            Exame exame = requisicao.toExame();
            this.exameRepository.save(exame);
           
            return new ModelAndView("redirect:/exames/" + exame.getId());
        }
    }


    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Exame> optional = this.exameRepository.findById(id);

        if (optional.isPresent()) {
            Exame exame = optional.get();

            ModelAndView mv = new ModelAndView("exames/show");
            mv.addObject("exame", exame);
            mv.addObject("listaExame", StatusExame.values());

            return mv;
        }
        // não achou um registro na tabela Exame com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Exame DE ID "+ id + " $$$$$$$$$$$");
            return this.retornaErroExame("SHOW ERROR: Exame #" + id + " não encontrado no banco!");
        }
    }


    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormExame requisicao) {
        Optional<Exame> optional = this.exameRepository.findById(id);

        if (optional.isPresent()) {
            Exame exame = optional.get();
            requisicao.fromExame(exame);

            ModelAndView mv = new ModelAndView("exames/edit");
            mv.addObject("exameId", exame.getId());
            mv.addObject("listaExame", StatusExame.values());

            return mv;

        }
        // não achou um registro na tabela Exame com o id informado
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O Exame DE ID "+ id + " $$$$$$$$$$$");
            return this.retornaErroExame("EDIT ERROR: Exame #" + id + " não encontrado no banco!");
        }
    }


    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormExame requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("exames/edit");
            mv.addObject("exameId", id);
            mv.addObject("listaExame", StatusExame.values());
            
            return mv;
        }
        else {
            Optional<Exame> optional = this.exameRepository.findById(id);

            if (optional.isPresent()) {
                Exame exame = requisicao.toExame(optional.get());
                this.exameRepository.save(exame);

                return new ModelAndView("redirect:/exames/" + exame.getId());
            }
            // não achou um registro na tabela Exame com o id informado
            else {
                System.out.println("############ NÃO ACHOU O EXAME DE ID "+ id + " ############");
                return this.retornaErroExame("UPDATE ERROR: Exame #" + id + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/exames");

        try {
            this.exameRepository.deleteById(id);
            mv.addObject("mensagem", "Exame #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.retornaErroExame("DELETE ERROR: Exame #" + id + " não encontrado no banco!");
        }

        return mv;
    }

    private ModelAndView retornaErroExame(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/exames");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
