const boardElement = document.getElementById('chessboard');
const restartButton = document.getElementById('restart');
const turnIndicator = document.getElementById('current-turn');
const totalGamesElement = document.getElementById('total-games');
const whiteCounter = document.getElementById('white-counter');
const blackCounter = document.getElementById('black-counter');
const game = new Chess();

let totalGames = 0;     //총 게임 수
let whiteWins = 0;      //승리
let blackWins = 0;      //패배

const PIECES = {
    'k': { 'w': '♔', 'b': '♚' },
    'q': { 'w': '♕', 'b': '♛' },
    'r': { 'w': '♖', 'b': '♜' },
    'b': { 'w': '♗', 'b': '♝' },
    'n': { 'w': '♘', 'b': '♞' },
    'p': { 'w': '♙', 'b': '♟' }
};

function updateTurnIndicator() {
    turnIndicator.textContent = game.turn() === 'w' ? '흰색' : '검은색';
    turnIndicator.style.color = game.turn() === 'w' ? '#4CAF50' : '#FFFFFF';
}

function updateStats() {
    whiteCounter.textContent = `White Wins: ${whiteWins}`;
    blackCounter.textContent = `Black Wins: ${blackWins}`;
    totalGamesElement.textContent = totalGames;
}

function checkGameEnd() {
    if (game.in_checkmate()) {
        const winner = game.turn() === 'w' ? 'black' : 'white';
        if (winner === 'white') {
            whiteWins++;
        } else {
            blackWins++;
        }
        totalGames++;
        updateStats();
        
        // 게임 종료 알림
        alert(`게임 종료!\n${winner === 'white' ? '플레이어' : 'AI'} 가 승리했습니다!`);
        return true;
    } else if (game.in_draw() || game.in_stalemate() || game.in_threefold_repetition()) {
        totalGames++;
        updateStats();
        alert('무승부입니다!');
        return true;
    }
    return false;
}

function createBoard() {
    boardElement.innerHTML = '';
    for (let row = 0; row < 8; row++) {
        for (let col = 0; col < 8; col++) {
            const square = document.createElement('div');
            square.className = `square ${(row + col) % 2 === 0 ? 'white' : 'black'}`;
            square.dataset.position = `${String.fromCharCode(97 + col)}${8 - row}`;
            boardElement.appendChild(square);
        }
    }
    updateBoard();
    updateTurnIndicator();
}

function updateBoard() {
    const squares = boardElement.querySelectorAll('.square');
    squares.forEach(square => square.innerHTML = '');

    const position = game.board();
    position.forEach((row, rowIndex) => {
        row.forEach((piece, colIndex) => {
            if (piece) {
                const square = squares[rowIndex * 8 + colIndex];
                const pieceDiv = document.createElement('div');
                pieceDiv.className = 'piece';
                pieceDiv.textContent = PIECES[piece.type][piece.color];
                pieceDiv.draggable = true;
                square.appendChild(pieceDiv);
            }
        });
    });
}

function aiMove() {
    const moves = game.moves();
    if (moves.length > 0) {
        const move = moves[Math.floor(Math.random() * moves.length)];
        game.move(move);
        updateBoard();
        updateTurnIndicator();
        checkGameEnd();
    }
}

let draggedPiece = null;
let sourceSquare = null;

boardElement.addEventListener('dragstart', (e) => {
    const piece = e.target.closest('.piece');
    if (piece) {
        draggedPiece = piece;
        sourceSquare = piece.parentNode.dataset.position;
    }
});

boardElement.addEventListener('dragover', (e) => {
    e.preventDefault();
});

boardElement.addEventListener('drop', (e) => {
    e.preventDefault();
    if (!draggedPiece || !sourceSquare) return;
    
    const targetSquare = e.target.closest('.square');
    if (!targetSquare) return;

    const move = {
        from: sourceSquare,
        to: targetSquare.dataset.position,
        promotion: 'q'
    };

    try {
        const result = game.move(move);
        if (result) {
            updateBoard();
            updateTurnIndicator();
            if (!checkGameEnd()) {
                setTimeout(() => {
                    aiMove();
                }, 300);
            }
        }
    } catch (err) {
        console.error('Invalid move:', err);
    }
    
    draggedPiece = null;
    sourceSquare = null;
});

restartButton.addEventListener('click', () => {
    game.reset();
    updateBoard();
    updateTurnIndicator();
});

createBoard();
updateStats();