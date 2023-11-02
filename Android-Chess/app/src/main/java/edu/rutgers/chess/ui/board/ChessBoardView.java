package edu.rutgers.chess.ui.board;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import edu.rutgers.chess.game.board.Board;
import edu.rutgers.chess.game.piece.Piece;

public class ChessBoardView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

	private int boardWidth, boardHeight;
	private int cellSize;
	private Paint blackPaint, whitePaint;
	private Drawable[][] board = new Drawable[8][8];
	private OnSquareTouchListener mListener;

	public ChessBoardView(Context context) {
		super(context);
		init();
	}

	public ChessBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ChessBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		blackPaint = new Paint();
		blackPaint.setColor(Color.DKGRAY);
		whitePaint = new Paint();
		whitePaint.setColor(Color.WHITE);
		getHolder().addCallback(this);
		setOnTouchListener(this);
	}

	public void setOnSquareTouchListener(OnSquareTouchListener listener) {
		mListener = listener;
	}

	public void putPiece(int row, int col, Piece piece) {
		board[row][col] = piece.getPieceImage(getContext());
		draw();
	}

	public void putPieceNoDraw(int row, int col, Piece piece) {
		board[row][col] = piece.getPieceImage(getContext());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		boardWidth = Math.max((getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight()), MeasureSpec.getSize(widthMeasureSpec));
		boardHeight = Math.max((getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom()), MeasureSpec.getSize(heightMeasureSpec));
		cellSize = boardWidth / 8;
		setMeasuredDimension(boardWidth, boardHeight);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		draw();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		draw();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	private void draw() {
		Canvas canvas = getHolder().lockCanvas();
		if (canvas == null) return;

		canvas.drawColor(Color.WHITE);
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++)
				if ((row + col) % 2 == 0) canvas.drawRect(col * cellSize, row * cellSize,
						(col + 1) * cellSize, (row + 1) * cellSize, whitePaint);
				else canvas.drawRect(col * cellSize, row * cellSize,
						(col + 1) * cellSize, (row + 1) * cellSize, blackPaint);

		// Draw pieces
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++)
				if (board[row][col] != null) {
					Drawable piece = board[row][col];
					piece.setBounds(col * cellSize, row * cellSize, (col + 1) * cellSize, (row + 1) * cellSize);
					piece.draw(canvas);
				}

		getHolder().unlockCanvasAndPost(canvas);
	}

	public void updateBoard(Board board) {
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++) {
				Piece piece = board.getBoard().get(row).get(col).getPiece();
				if (piece != null) putPieceNoDraw(row, col, piece);
			}
		draw();
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN)
			if (mListener != null) mListener.onSquareTouch(((int) (motionEvent.getY() / (getHeight() / 8))), ((int) (motionEvent.getX() / (getWidth() / 8))));
		return true;
	}
}
