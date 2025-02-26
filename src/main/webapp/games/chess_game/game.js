function showValidMoves(sourceSquare) {
    // 이동 가능한 모든 위치 계산
    const validMoves = game.moves({ square: sourceSquare, verbose: true });

    // 모든 valid move 위치에 강조 표시 추가
    validMoves.forEach(move => {
        const targetSquare = document.querySelector(`[data-position="${move.to}"]`);
        if (targetSquare) {
            const highlight = document.createElement('div');
            highlight.className = 'highlight';
            targetSquare.appendChild(highlight);
        }
    });
}

function clearHighlights() {
    const highlights = document.querySelectorAll('.highlight');
    highlights.forEach(highlight => highlight.remove());
}

// CSS 스타일 추가
const style = document.createElement('style');
style.innerHTML = `
    .highlight {
        width: 20px;
        height: 20px;
        border-radius: 50%;
        background-color: rgba(0, 255, 0, 0.5);
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        pointer-events: none;
    }
    .square {
        position: relative; /* highlight positioning을 위한 설정 */
    }
`;
document.head.appendChild(style);

// 드래그 시작 시 유효 이동 위치 표시
boardElement.addEventListener('dragstart', (e) => {
    if (e.target.classList.contains('piece')) {
        draggedPiece = e.target;
        sourceSquare = e.target.parentNode.dataset.position;
        showValidMoves(sourceSquare);
    }
});

// 드래그 종료 시 강조 제거
boardElement.addEventListener('dragend', clearHighlights);

// 드롭 시 강조 제거 및 수 처리
boardElement.addEventListener('drop', (e) => {
    e.preventDefault();
    const targetSquare = e.target.closest('.square');
    if (!targetSquare || !sourceSquare) return;

    const move = {
        from: sourceSquare,
        to: targetSquare.dataset.position,
        promotion: 'q' // 승격 가능성 추가
    };

    try {
        const result = game.move(move);
        if (result) {
            updateBoard();
            setTimeout(() => {
                clearHighlights(); // 드롭 후 강조 제거
                aiMove(); // AI 수
            }, 300);
        }
    } catch (err) {
        console.error('Invalid move:', err);
        clearHighlights(); // 무효한 움직임 후 강조 제거
    }
});
