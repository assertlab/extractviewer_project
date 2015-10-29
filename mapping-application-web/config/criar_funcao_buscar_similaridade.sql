--artificio rudimentar para tratamento das strings para busca da similaridade do titulo
UPDATE mapeamento.estudo SET titulo_limpo = UPPER(unaccent(regexp_replace(titulo, '[^a-zA-Z]', '', 'g')));

--busca rudmentar para identificar similaridade
SELECT sum(total) FROM 
(select titulo, count(titulo) as total from mapeamento.estudo GROUP BY titulo  order by total desc) AS TEMP WHERE total > 1;

--instala as funcoes que permitem busca por similaridade. É necessário logar via console.
create extension fuzzystrmatch;
create extension pg_trgm;
create extension unaccent;

--criacao do indice
CREATE INDEX ON mapeamento.estudo USING GIN(titulo_limpo gin_trgm_ops);

--Cria a funcao para identificar similaridades
CREATE FUNCTION buscar_similares() RETURNS integer AS $$
DECLARE
    registros_em_analise RECORD;
    registros RECORD; 
    similaridade double precision;
    maior_similaridade double precision;
    id_maior_similaridade bigint;
BEGIN
        
    FOR registros_em_analise IN SELECT * FROM mapeamento.estudo ORDER BY titulo LOOP

        -- Agora "registros_em_analise" te um registro de estudo 
        maior_similaridade := 0.0;

        FOR registros IN SELECT * FROM mapeamento.estudo ORDER BY titulo LOOP
            similaridade := similarity(registros_em_analise.titulo, Registros.titulo);
            
            IF similaridade > maior_similaridade THEN
                maior_similaridade := similaridade;
                id_maior_similaridade := registros.id_estudo;
            END IF;

        END LOOP;        

        IF registros_em_analise IS NOT NULL THEN             
             EXECUTE 'UPDATE mapeamento.estudo SET id_maior_similaridade = ' || id_maior_similaridade || ' WHERE id_estudo =' || registros_em_analise.id_estudo;
        END IF;
                
    END LOOP;
    
    RETURN 1;
END;
$$ LANGUAGE plpgsql