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
                                <div class="columns content">
                                    <div class="column">
                                        <h4>Professor</h4>
                                        <b-field>
                                            <b-upload v-model="professor"
                                                accept=".csv" required validationMessage="Por valor selecione um arquivo"
                                                drag-drop>
                                                <section class="section">
                                                    <div class="content has-text-centered">
                                                        <p>
                                                            <b-icon
                                                                icon="upload"
                                                                size="is-large">
                                                            </b-icon>
                                                        </p>
                                                        <p>Arraste clique para fazer upload</p>
                                                    </div>
                                                </section>
                                            </b-upload>
                                        </b-field>
                                    </div>
                                    <div class="column">
                                        <h4>Disciplinas</h4>
                                        <b-field>
                                            <b-upload v-model="disciplines" 
                                                accept=".csv" required validationMessage="Por valor selecione um arquivo"
                                                drag-drop>
                                                <section class="section">
                                                    <div class="content has-text-centered">
                                                        <p>
                                                            <b-icon
                                                                icon="upload"
                                                                size="is-large">
                                                            </b-icon>
                                                        </p>
                                                        <p>Arraste clique para fazer upload</p>
                                                    </div>
                                                </section>
                                            </b-upload>
                                        </b-field>
                                    </div>
                                </div>
                                <b-button type="is-dark" @click="submitData">
                                    Enviar arquivos
                                </b-button>
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
        },
        methods: {
            async submitData() {
                if (!this.professor || !this.disciplines) {
                    //TODO: mostrar um popup de erro para o usuario
                    console.log("Erro: professor ou disciplinas nulas");
                    return;
                }
                const [professorArray, disciplinesArray] = await Promise.all([
                    this.getProfessorArray(),
                    this.getDisciplinesArray()
                ]);

                const professorDisciplinesMap = disciplinesArray.reduce((map, discipline) => {
                    const professorCode = discipline.professor_code;
                    if (!map[professorCode]) {
                        map[professorCode] = [];
                    }
                    map[professorCode].push(discipline);
                    return map;
                }, {});

                professorArray.forEach((professor) => {
                    const professorCode = professor.code;
                    if (professorDisciplinesMap[professorCode]) {
                        professor.disciplines = professorDisciplinesMap[professorCode];
                    }
                });

                console.log(JSON.stringify(professorArray));
                //TODO: enviar JSON para backend e mostrar resultado na página
            },
            async getProfessorArray() {
                try {
                    return await this.parseProfessorCSVToArray();
                } catch (error) {
                    console.error("Erro durante o processamento do arquivo CSV:", error);
                }
            },
            async getDisciplinesArray() {
                try {
                    return await this.parseDisciplineCSVToArray();
                } catch (error) {
                    console.error("Erro durante o processamento do arquivo CSV:", error);
                }
            },
            parseProfessorCSVToArray() {
                const reader = new FileReader();
                const professors = [];

                return new Promise((resolve, reject) => {
                    reader.onload = (event) => {
                        try {
                            const csvData = event.target.result;
                            const lines = csvData.split('\n');

                            for (let i = 1; i < lines.length - 1; i++) {
                                const [code, name, disciplines, mandatory_days, preference_days] = lines[i].split(';').map(value => value.trim());

                                const professor = {
                                    code,
                                    name,
                                    disciplines: disciplines.split(',').map(discipline => discipline.trim()).filter(discipline => discipline !== ''),
                                    mandatory_days: mandatory_days.split(',').map(day => day.trim()).filter(day => day !== ''),
                                    preference_days: preference_days.split(',').map(day => day.trim()).filter(day => day !== '')
                                };

                                professors.push(professor);
                            }

                            resolve(professors);
                        } catch (error) {
                            reject(error);
                        }
                    };

                    reader.onerror = (error) => {
                        reject(error);
                    };

                    reader.readAsText(this.professor);
                });
            },
            parseDisciplineCSVToArray() {
                const reader = new FileReader();
                const disciplines = [];

                return new Promise((resolve, reject) => {
                    reader.onload = (event) => {
                        try {
                            const csvData = event.target.result;
                            const lines = csvData.split('\n');

                            for (let i = 1; i < lines.length - 1; i++) {
                                const [discipline_code, description, professor_code, work_hours, credits,
                                    class_restriction,
                                    time_restriction] = lines[i].split(';').map(value => value.trim());

                                const discipline = {
                                    discipline_code,
                                    description,
                                    professor_code,
                                    work_hours,
                                    credits,
                                    class_restriction,
                                    time_restriction
                                };

                                disciplines.push(discipline);
                            }

                            resolve(disciplines);
                        } catch (error) {
                            reject(error);
                        }
                    };

                    reader.onerror = (error) => {
                        reject(error);
                    };

                    reader.readAsText(this.disciplines);
                });
            }
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