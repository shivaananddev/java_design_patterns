package com.anpra.java_design_patterns.solid.controller;

import com.anpra.java_design_patterns.solid.srp.SrpExample;
import com.anpra.java_design_patterns.solid.ocp.OcpExample;
import com.anpra.java_design_patterns.solid.lsp.LspExample;
import com.anpra.java_design_patterns.solid.isp.IspExample;
import com.anpra.java_design_patterns.solid.dip.DipExample;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solid")
public class SolidDemoController {

    private final SrpExample srpExample;
    private final OcpExample ocpExample;
    private final LspExample lspExample;
    private final IspExample ispExample;
    private final DipExample dipExample;

    public SolidDemoController(SrpExample srpExample, OcpExample ocpExample,
                               LspExample lspExample, IspExample ispExample, DipExample dipExample) {
        this.srpExample = srpExample;
        this.ocpExample = ocpExample;
        this.lspExample = lspExample;
        this.ispExample = ispExample;
        this.dipExample = dipExample;
    }

    @GetMapping("/srp")
    public String testSrp() {
        return srpExample.execute();
    }

    @GetMapping("/ocp")
    public String testOcp() {
        return ocpExample.execute();
    }

    @GetMapping("/lsp")
    public String testLsp() {
        return lspExample.execute();
    }

    @GetMapping("/isp")
    public String testIsp() {
        return ispExample.execute();
    }

    @GetMapping("/dip")
    public String testDip() {
        return dipExample.execute();
    }
}