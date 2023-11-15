<template id="generator-page">
    <div>
        <nav class="navbar docs-navbar is-spaced has-shadow is-dark">
            <div class="container">
                <div class="navbar-brand">
                    <div id="logo" class="navbar-item">
                        <img src="logo-udesc-alto-vale.jpg" alt="Logo UDESC">
                    </div>
                </div>
            </div>
        </nav>
        <section>
            <div class="container is-fullhd">
                <div class="content">
                    <div class="columns">
                        <div class="column">
                            <div class="box">
                                <h2 class="title">Gerador de grades</h2>
                                <span>
                                    Faça upload dos arquivos no formato <em>CSV</em> contendo as informações dos professores e das disciplinas.
                                </span>
        
                                <h3>CSV Professor</h3>
                                <b-field>
                                    <b-upload v-model="professor"
                                        multiple
                                        drag-drop>
                                        <section class="section">
                                            <div class="content has-text-centered">
                                                <p>
                                                    <b-icon
                                                        icon="upload"
                                                        size="is-large">
                                                    </b-icon>
                                                </p>
                                                <p>Arraste clique para fazer upload do arquivo</p>
                                            </div>
                                        </section>
                                    </b-upload>
                                </b-field>

                                <h3>CSV Disciplinas</h3>
                                <b-field>
                                    <b-upload v-model="disciplines"
                                        multiple
                                        drag-drop>
                                        <section class="section">
                                            <div class="content has-text-centered">
                                                <p>
                                                    <b-icon
                                                        icon="upload"
                                                        size="is-large">
                                                    </b-icon>
                                                </p>
                                                <p>Arraste clique para fazer upload do arquivo</p>
                                            </div>
                                        </section>
                                    </b-upload>
                                </b-field>
                            </div>
                        </div>
                        <div id="result" class="column">
                            <div class="box">
                                <h2 class="title">Resultado</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
    Vue.component("generator-page", {
        template: "#generator-page",
        data() {
            return {
                professor: {},
                disciplines: {}
            };
        }
    });
</script>

<style>
    #logo {
        background-color: white;
        border-radius: 4px;
        margin-left: 12px
    }

    #result div {
        min-height: 100%;
    }

    .content {
        padding-top: 25px;
    }
</style>