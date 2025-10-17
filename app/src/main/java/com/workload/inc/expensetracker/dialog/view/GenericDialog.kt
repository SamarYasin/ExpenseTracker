package com.workload.inc.expensetracker.dialog.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.workload.inc.expensetracker.databinding.GenericDialogBinding
import com.workload.inc.expensetracker.dialog.WarningModel
import com.workload.inc.expensetracker.utils.goneView
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showView

class GenericDialog(
    private val warningModel: WarningModel,
    private val onPositive: () -> Unit,
    private val onNegative: () -> Unit
) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.90).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBinding = GenericDialogBinding.inflate(layoutInflater)

        dialogBinding.dialogTitleTV.goneView()
        dialogBinding.warningIV.showView()
        dialogBinding.dialogMessage.showView()
        dialogBinding.positiveBtn.showView()
        dialogBinding.negativeBtn.showView()

        dialogBinding.dialogMessage.text = warningModel.dialogMessage
        dialogBinding.positiveBtnTV.text = warningModel.positiveButtonText
        dialogBinding.negativeBtnTV.text = warningModel.negativeButtonText
        dialogBinding.warningIV.setImageResource(warningModel.dialogWarningErrorIconId)

        dialogBinding.positiveBtn.setSafeOnClickListener {
            onPositive()
            dismiss()
        }

        dialogBinding.negativeBtn.setSafeOnClickListener {
            onNegative()
            dismiss()
        }

        dialogBinding.crossIVBtn.setSafeOnClickListener { dismiss() }

        val dialog = Dialog(requireContext())
        dialog.setContentView(dialogBinding.root)

        // make background transparent (so rounded corners work)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}