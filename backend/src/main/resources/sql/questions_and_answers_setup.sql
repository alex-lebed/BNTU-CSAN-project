INSERT INTO
    question(id, question_text)
VALUES
    (1, 'Год основания БНТУ?'),
    (2, 'Сколько факультетов в БНТУ?'),
    (3, 'Один из старейших факультетов БНТУ?'),
    (4, 'Как звали первого ректора учебного заведения?'),
    (5, 'Кто был назначен ректором БНТУ в 2017 году?'),
    (6, 'Как назывался БНТУ в период с 1920 до 1991 года?'),
    (7, 'Как назывался БНТУ в период с 1991 до 2002 года?');

INSERT INTO
    answer(question_id, answer_text, is_correct)
VALUES
    -- Год основания БНТУ?
    (1, '1920', TRUE),
    (1, '1922', FALSE),
    (1, '1918', FALSE),
    (1, '1925', FALSE),
    -- Сколько факультетов в БНТУ?
    (2, '16', TRUE),
    (2, '12', FALSE),
    (2, '14', FALSE),
    (2, '18', FALSE),
    -- Один из старейших факультетов БНТУ?
    (3, 'Энергетический', TRUE),
    (3, 'Механико-технологический', FALSE),
    (3, 'Автотракторный', FALSE),
    (3, 'Информационных технологий и робототехники', FALSE),
    -- Как звали первого ректора учебного заведения?
    (4, 'Никанор Казимирович Ярошевич', TRUE),
    (4, 'Иван Иосифович Дружеловский', FALSE),
    (4, 'Дмитрий Иустинович Горин', FALSE),
    (4, 'Самуил Иоахимович Позняк', FALSE),
    -- Кто был назначен ректором БНТУ в 2017 году?
    (5, 'Сергей Васильевич Харитончик', TRUE),
    (5, 'Борис Михайлович Хрусталёв', FALSE),
    (5, 'Михаил Иванович Демчук', FALSE),
    (5, 'Валентин Дмитриевич Ткачёв', FALSE),
    -- Как назывался БНТУ в период с 1920 до 1991 года?
    (6, 'БГПИ', TRUE),
    (6, 'БГПА', FALSE),
    (6, 'БГПУ', FALSE),
    (6, 'БГПТ', FALSE),
    -- Как назывался БНТУ в период с 1991 до 2002 года?
    (7, 'БГПА', TRUE),
    (7, 'БГПИ', FALSE),
    (7, 'БГПУ', FALSE),
    (7, 'БГПТ', FALSE);

